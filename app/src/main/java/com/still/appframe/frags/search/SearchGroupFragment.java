package com.still.appframe.frags.search;


import com.still.appframe.R;
import com.still.appframe.activitys.SearchActivity;
import com.still.common.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchGroupFragment extends Fragment implements SearchActivity.SearchFragment{


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    public void search(String content) {

    }
}
