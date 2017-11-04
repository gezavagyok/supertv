package tek.gezacsorba.supertv.main.channel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tek.gezacsorba.supertv.R;
import tek.gezacsorba.supertv.network.Channel;
import tek.gezacsorba.supertv.network.Programme;
import tek.gezacsorba.supertv.util.TimeUtils;

/**
 * Created by geza on 11/4/17.
 */

public class ChannelView extends RecyclerView {

    public ChannelView(Context context) {
        super(context);
    }

    public ChannelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChannelView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static ChannelView from(Context context, Channel channel) {
        ChannelView view = new ChannelView(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        view.setAdapter(new ChannelAdapter(channel.getTitle(), channel.getProgramme()));
        return view;
    }

    public static class ChannelAdapter extends RecyclerView.Adapter<ViewHolder> {

        private static final int VIEW_TYPE_HEADER = 0;
        private static final int VIEW_TYPE_LIST_ITEM = 1;

        final String title;
        final List<Programme> programmes;

        public ChannelAdapter(String title, List<Programme> programmes) {
            this.programmes = programmes;
            this.title = title;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case VIEW_TYPE_HEADER:
                    return new ChannelNameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false));
                case VIEW_TYPE_LIST_ITEM:
                    return new ChannelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programme, parent, false));
                default:
                    // should not happen
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder instanceof ChannelNameViewHolder) {
                ((ChannelNameViewHolder) holder).titleTextView.setText(title);
            }
            if (holder instanceof ChannelViewHolder) {
                Programme programme = programmes.get(position - 1); // remember, there's a header.
                ((ChannelViewHolder) holder).startingTextView.setText(TimeUtils.getHoursAndMinutes(programme.getStartDate()));
                ((ChannelViewHolder) holder).titleTextView.setText(programme.getTitle());
            }
        }

        @Override
        public int getItemCount() {
            return programmes == null ? 1 : programmes.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_LIST_ITEM;
        }
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_starting)
        TextView startingTextView;

        @BindView(R.id.tv_title)
        TextView titleTextView;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ChannelNameViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView titleTextView;

        public ChannelNameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
