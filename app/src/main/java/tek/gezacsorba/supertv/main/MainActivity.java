package tek.gezacsorba.supertv.main;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import tek.gezacsorba.supertv.BaseActivity;
import tek.gezacsorba.supertv.BuildConfig;
import tek.gezacsorba.supertv.R;
import tek.gezacsorba.supertv.di.DaggerMainComponent;
import tek.gezacsorba.supertv.di.MainModule;
import tek.gezacsorba.supertv.main.channel.ChannelView;
import tek.gezacsorba.supertv.network.Channel;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.programme_container)
    LinearLayout linearLayout;

    @BindView(R.id.empty_view)
    View empty;

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void injectDependencies() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        presenter.loadProgramme();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayChannels(List<Channel> channels) {
        if (checkEmpty(channels)) return;

        for (Channel channel : channels) {
            View view = ChannelView.from(this, channel);
            linearLayout.addView(view);
        }
    }

    private boolean checkEmpty(List<Channel> channels) {
        empty.setVisibility(View.GONE);
        if (channels.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
