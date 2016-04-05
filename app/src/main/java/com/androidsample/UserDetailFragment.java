package com.androidsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidsample.adapters.UserDetailAdapter;
import com.androidsample.common.BaseFragment;
import com.androidsample.model.UserDetail;
import com.androidsample.task.ReadFromFileTask;

import org.w3c.dom.Text;

/**
 * Created by Aditya on 04-Apr-16.
 */
public class UserDetailFragment extends BaseFragment implements ReadFromFileTask.FileReadListener{

    private RecyclerView mRecyclerView;
    private UserDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressbar;
    private TextView mTextView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        mTextView = (TextView) rootView.findViewById(R.id.friendName);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }

    @Override
    protected void loadData() {
        mProgressbar.setVisibility(View.VISIBLE);
        ReadFromFileTask task = new ReadFromFileTask(this,UserDetail.class);
        task.execute(getResources().getString(R.string.file_name));
    }

    @Override
    public void onFileReadSuccess(Object response) {
        UserDetail detail = (UserDetail)response;
        if(detail!=null) {
            mAdapter = new UserDetailAdapter(detail);
            mRecyclerView.setAdapter(mAdapter);
            mTextView.setText(detail.name);
            mProgressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFileReadFailure() {

    }
}
