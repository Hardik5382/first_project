package hardik.invesmenthandle.com.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hardik.invesmenthandle.com.R;
import hardik.invesmenthandle.com.modal.PolicyAcivity;
import hardik.invesmenthandle.com.modal.User;

import static android.content.ContentValues.TAG;

public class adapter extends RecyclerView.Adapter<adapter.UserViewHolder> {

    private List<PolicyAcivity>  policyAcivities;

    public adapter(List<PolicyAcivity> listUsers) {
        this.policyAcivities = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_policy_list, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        PolicyAcivity policyAcivity = policyAcivities.get(position);
        holder.textViewName.setText(policyAcivity.getpName());
    }

    @Override
    public int getItemCount() {
        return policyAcivities.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;

        public UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.policy_card_txt_1);
        }
    }


}