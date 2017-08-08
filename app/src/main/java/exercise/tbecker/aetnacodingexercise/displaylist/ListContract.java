package exercise.tbecker.aetnacodingexercise.displaylist;

import java.util.List;

import exercise.tbecker.aetnacodingexercise.models.MyData;
import exercise.tbecker.aetnacodingexercise.models.ResponseItem;

/**
 * Created by Android on 8/7/2017.
 */

public interface ListContract {

    interface View {
        void showDataErrorMessage();

        void showNetworkErrorMessage();

        void showUserList(List<MyData> listInfo);
    }

    interface Presenter {
        void populateUserList(String input);
    }

}
