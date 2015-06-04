package audio.rabid.dev.homeautomation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by charles on 6/3/15.
 *
 * I got tired of building the same ArrayAdapter over and over.
 *
 * Use this one to get an {@link EasyArrayAdapter.AdapterDrawCallback} when the sub item needs to
 * be drawn.
 */
public class EasyArrayAdapter<T> extends ArrayAdapter<T> {

    private Context context;
    private int layoutId;
    private List<T> list;
    private HashMap<String, Integer> viewIds;

    AdapterDrawCallback<T> callback;

    public interface AdapterDrawCallback<T> {
        /**
         * @param object The model
         * @param viewSet The child views to update
         */
        void onDrawView(T object, HashMap<String, View> viewSet);
    }

    /**
     *
     * @param context the parent context
     * @param layoutId the ID of the layout resource to use as the view for each item
     * @param viewIds a set of resource IDs for the child views in the layout
     * @param list the collection of backing objects
     * @param callback called when time to draw an item
     */
    public EasyArrayAdapter(Context context, int layoutId, HashMap<String, Integer> viewIds,
                            @Nullable List<T> list, @Nullable AdapterDrawCallback<T> callback){
        super(context, layoutId, (list==null ? new ArrayList<T>() : list));
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        this.viewIds = viewIds;
        this.callback = callback;
    }


    private HashMap<String, View> getViewSet(View v){
        HashMap<String, View> viewSet = new HashMap<>();
        Iterator<Map.Entry<String, Integer>> it = viewIds.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = it.next();
            viewSet.put(pair.getKey(), v.findViewById(pair.getValue()));
            it.remove(); // avoids a ConcurrentModificationException
        }
        return viewSet;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View rowView;
        HashMap<String, View> viewSet;
        if (view == null) {
            rowView = inflater.inflate(layoutId, viewGroup, false);
            viewSet = getViewSet(rowView);
            rowView.setTag(viewSet);
        } else {
            rowView = view;
            viewSet = (HashMap<String, View>) rowView.getTag();
            if (viewSet == null) {
                //for some reason, no holder attached
                viewSet = getViewSet(rowView);
                rowView.setTag(viewSet);
            }
        }
        if(callback != null){
            callback.onDrawView(list.get(position), viewSet);
        }
        return rowView;
    }
}
