package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.StickyRecyclerAdapter;

@Module
public abstract class UnfoldedMainModule {
    @Binds
    public abstract AppCompatActivity bindContext(UnfoldedMainActivity unfoldedMainActivity);

    @Binds
    abstract UnfoldedMainContract.View vindView(UnfoldedMainActivity unfoldedMainActivity);

    @Binds
    public abstract AdapterContract.View<Item> bindAdapterView(StickyRecyclerAdapter stickyRecyclerAdapter);

    @Binds
    public abstract UnfoldedMainContract.Presenter bindUnfoldedMainPresenter(UnfoldedMainPresenter unfoldedMainPresenter);
}
