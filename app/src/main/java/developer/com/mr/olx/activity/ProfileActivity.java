package developer.com.mr.olx.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.ModelClasses.ClsSettingNameVal;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.SettingAdapter;


public class ProfileActivity extends AppCompatActivity {

    private List<ClsSettingNameVal> mList;
    private RecyclerView mRv;
    private SettingAdapter mCv;
    ImageView iv_back;
    ImageView iv_edit_profile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        main();
    }

    private void main() {
        iv_edit_profile = findViewById(R.id.iv_edit_profile);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
            }
        });
        mRv = findViewById(R.id.mRv);
        mRv.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
        mRv.addItemDecoration(new DividerItemDecoration(mRv.getContext(),
                DividerItemDecoration.VERTICAL));
        viewData();
    }

    void viewData() {

        mList = new ArrayList<>();

        mList.add(new ClsSettingNameVal(R.drawable.ic_byu_packge,
                "Buy Package", 1));

        mList.add(new ClsSettingNameVal(R.drawable.ic_active_pckg,
                "My Orders / Active Package", 2));

        mList.add(new ClsSettingNameVal(R.drawable.ic_language,
                "Language", 3));

        mList.add(new ClsSettingNameVal(R.drawable.ic_invite_friend,
                "Invite friends", 4));

        mList.add(new ClsSettingNameVal(R.drawable.ic_billing_details,
                "Billing Details", 5));

        mList.add(new ClsSettingNameVal(R.drawable.ic_settings,
                "Settings", 6));

        mList.add(new ClsSettingNameVal(R.drawable.ic_help_support,
                "Help/Support", 7));

        mList.add(new ClsSettingNameVal(R.drawable.ic_logout,
                "Logout", 8));

        mCv = new SettingAdapter(ProfileActivity.this, mList, clsSettingNames -> {
            int obj = clsSettingNames.getPosition();
            if (obj == 1) {
                startActivity(new Intent(getApplicationContext(), BuyPackageActivity.class));
            }else if (obj == 3) {
                startActivity(new Intent(getApplicationContext(), SelectLanguageActivity.class));
            } else if (obj == 6) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            } else if (obj == 7) {
                startActivity(new Intent(getApplicationContext(), HelpSupportActivity.class));
            }
        });
        mRv.setAdapter(mCv);
    }
}
