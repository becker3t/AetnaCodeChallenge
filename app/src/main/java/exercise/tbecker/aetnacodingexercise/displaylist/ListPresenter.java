package exercise.tbecker.aetnacodingexercise.displaylist;

import java.util.ArrayList;
import java.util.List;

import exercise.tbecker.aetnacodingexercise.models.Item;
import exercise.tbecker.aetnacodingexercise.models.MyData;
import exercise.tbecker.aetnacodingexercise.models.ResponseItem;
import exercise.tbecker.aetnacodingexercise.network.MyRetrofitService;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Android on 8/7/2017.
 */

public class ListPresenter implements ListContract.Presenter {

    private List<MyData> listMyData;

    private MyRetrofitService service;

    private ListContract.View view;

    public ListPresenter(ListContract.View view, MyRetrofitService service) {
        this.view = view;
        this.service = service;
        listMyData = new ArrayList<>();
    }

//    public Bundle getDataBundle (int position) {
//        Bundle myBundle = new Bundle();
//        myBundle.putString(MainActivity.IMG_URL_TAG, listMyData.get(position).getImageUrl());
//        myBundle.putString(MainActivity.NAME_TAG, listMyData.get(position).getName());
//        myBundle.putString(MainActivity.ADDRESS_TAG, listMyData.get(position).getAddress());
//        myBundle.putString(MainActivity.EMAIL_TAG, listMyData.get(position).getEmail());
//        return myBundle;
//    }

    @Override
    public void populateUserList(String input) {
        Call<ResponseItem> call = service.getData(1, "any", "json", input);
        call.enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseItem> call, retrofit2.Response<ResponseItem> response) {
                if(response.isSuccessful()) {
                    ResponseItem item = response.body();

                    for(Item i: item.getItems()) {
                        final String title = i.getTitle();
                        final String userImgUrl = i.getMedia().getM();

                        listMyData.add(new MyData(title, userImgUrl));
                    }

                    view.showUserList(listMyData);
                }
                else {
                    view.showDataErrorMessage();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseItem> call, Throwable t) {
                //do stuff on failure
                view.showNetworkErrorMessage();
            }
        });
    }
}
