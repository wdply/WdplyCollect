package com.example.ply.wdplycollect.activity.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/** 搜索demo
 * Created by ljzyuhenda on 16/7/22.
 */
public class SearchActivity extends Activity implements TextWatcher, View.OnClickListener, LoadMoreRecyclerView.onLoadMoreListener, onRecyclerViewItemClickListener {
    private static final String TAG = "SearchActivity";
    @BindView(R.id.rl_right_topbar)
    RelativeLayout rl_right_topbar;
    @BindView(R.id.tv_right_topbar)
    TextView tv_right_topbar;
    @BindView(R.id.et_search_topbar)
    EditText et_search_topbar;
    @BindView(R.id.rcv_search)
    LoadMoreRecyclerView rcv_search;
    @BindView(R.id.ll_down_no)
    LinearLayout ll_down_no;

    private SearchListAdapter mSearchListAdapter;
    private int mCurrentPage;
//    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initDatas();
        setListener();
    }

    private void initDatas() {
        tv_right_topbar.setText(R.string.netschool_dialog_cancel);
//        mCompositeSubscription = RxUtils.getNewCompositeSubIfUnsubscribed(mCompositeSubscription);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_search.setLayoutManager(linearLayoutManager);

        mSearchListAdapter = new SearchListAdapter(this);
        rcv_search.setAdapter(mSearchListAdapter);
        rcv_search.setAutoLoadMoreEnable(false);
        rcv_search.setOnLoadMoreListener(this);

        //搜索点击事件
//        RxView.clicks(rl_right_topbar).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Subscriber<Void>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onNext(Void aVoid) {
//                InputMethodManager inputManager = (InputMethodManager) tv_right_topbar.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputManager.hideSoftInputFromWindow(tv_right_topbar.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//
//                if (getResources().getString(R.string.search).equals(tv_right_topbar.getText().toString())) {
//                    mSearchListAdapter.setKeyWords(et_search_topbar.getText().toString());
//                    //搜索
//                    loadMoreData(true);
//                    tv_right_topbar.setText(R.string.netschool_dialog_cancel);
//                } else {
//                    finish();
//                }
//            }
//        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            if (getResources().getString(R.string.netschool_dialog_cancel).equals(tv_right_topbar.getText())) {
                tv_right_topbar.setText(R.string.search);
            }
        } else {
            tv_right_topbar.setText(R.string.netschool_dialog_cancel);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    private void setListener() {
        et_search_topbar.addTextChangedListener(this);
        mSearchListAdapter.setOnRecyclerViewItemClickListener(this);
        et_search_topbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (ll_down_no.getVisibility() == View.VISIBLE) {
                            ll_down_no.setVisibility(View.GONE);
                        }
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void onLoadMore() {
        loadMoreData(false);
    }

    @Override
    public void onItemClick(View view, int position, int resId) {
        //搜索条目点击事件
//        ExerciseBean.ExerciseInfoBean infoBean = mSearchListAdapter.getItem(position);
//        Bundle bundle = new Bundle();
//        bundle.putString("exerciseIdList", "" + infoBean.id);
//        ArenaExamActivity.show(SearchActivity.this,
//                ArenaConstant.EXAM_ENTER_FORM_TYPE_JIEXI_SINGLE, bundle);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    private void loadMoreData(boolean isReset) {
        if (isReset) {
            mSearchListAdapter.getDataList().clear();
            rcv_search.notifyDataSetChanged();

            rcv_search.setAutoLoadMoreEnable(true);
            mCurrentPage = 0;
        }

        loadDatas(mCurrentPage, isReset);
    }

    private void loadDatas(int page, final boolean isReset) {
//        Observable<ExerciseBean> searchedExersicesObservable = RetrofitManager.getInstance().getService()
//                .getSearchedExersices(et_search_topbar.getText().toString(), page, 20, -1);
//        Subscription subscription = searchedExersicesObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ExerciseBean>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        CommonUtils.showToast(R.string.networkerror);
//                        rcv_search.notifyMoreFinished(true);
//                    }
//
//                    @Override
//                    public void onNext(ExerciseBean exerciseData) {
//                        if ("1110002".equals(exerciseData.code)) {
//                            CommonUtils.showToast(R.string.sessionOutOfDateInfo);
//                        } else if ("1000000".equals(exerciseData.code)) {
//                            if (exerciseData == null || exerciseData.data.results == null || exerciseData.data.results.size() == 0) {
//                                if (isReset) {
//                                    ll_down_no.setVisibility(View.VISIBLE);
//                                } else {
//                                    CommonUtils.showToast(R.string.noMoreData);
//                                }
//                            } else {
//                                mSearchListAdapter.getDataList().addAll(exerciseData.data.results);
//                            }
//                        } else {
//                            CommonUtils.showToast(R.string.toast_error_unknown);
//                        }
//
//                        rcv_search.notifyMoreFinished(true);
//                    }
//                });
//
//        mCompositeSubscription.add(subscription);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxUtils.unsubscribeIfNotNull(mCompositeSubscription);
    }
}
