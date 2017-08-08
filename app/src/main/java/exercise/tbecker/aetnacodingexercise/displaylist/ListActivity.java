package exercise.tbecker.aetnacodingexercise.displaylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import exercise.tbecker.aetnacodingexercise.R;
import exercise.tbecker.aetnacodingexercise.adapter.MyRecyclerAdapter;
import exercise.tbecker.aetnacodingexercise.models.MyData;
import exercise.tbecker.aetnacodingexercise.models.ResponseItem;
import exercise.tbecker.aetnacodingexercise.network.MyRetrofitService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements ListContract.View, MyRecyclerAdapter.ListItemClickListener {

    private static final String TAG = ListActivity.class.getSimpleName() + "_TAG";

    private ListPresenter presenter;

    @BindView(R.id.search_et)
    EditText searchEditText;

    @BindView(R.id.results_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyRetrofitService service = retrofit.create(MyRetrofitService.class);

        presenter = new ListPresenter(this, service);

        ButterKnife.bind(this);
    }

    @Override
    public void showDataErrorMessage() {
        Log.d(TAG, "onFailure: Error retrieving network data.");
        Toast.makeText(this, "Error retrieving data.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Log.d(TAG, "onFailure: Error connecting to network.");
        Toast.makeText(this, "Network error. Check internet settings", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserList(List<MyData> listInfo) {
        updateRecyclerAdapter(listInfo);
    }

    @OnClick(R.id.search_btn)
    public void makeNetworkCall() {
        presenter.populateUserList(searchEditText.getText().toString());
    }

    private void updateRecyclerAdapter(List<MyData> list) {
        MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(list, this);
        recyclerAdapter.setListItemClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void OnItemClick(View v, int position) {
        Log.d(TAG, "OnItemClick: it works!!!!" + position);
    }
}
