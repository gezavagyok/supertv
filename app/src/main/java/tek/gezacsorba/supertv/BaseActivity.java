package tek.gezacsorba.supertv;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by geza on 11/4/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        injectDependencies();
        initView();
    }

    protected abstract void injectDependencies();

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView();
}
