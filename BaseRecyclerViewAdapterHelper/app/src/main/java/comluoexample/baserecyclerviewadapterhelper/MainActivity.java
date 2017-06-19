package comluoexample.baserecyclerviewadapterhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

import comluoexample.baserecyclerviewadapterhelper.adapt.QuickAdapter;
import comluoexample.baserecyclerviewadapterhelper.javaBean.MultyItemBean;

public class MainActivity extends AppCompatActivity implements  BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
//    private MyQuickAdapter quickAdapter;
    private static List<MultyItemBean>  data=new ArrayList<>();
    private QuickAdapter mquickAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int TOTAL_COUNTER = 30;

    private static final int PAGE_SIZE = 5;

    private int delayMillis = 1000;
    private int mCurrentCounter = 0;

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;
    private  OnItemSwipeListener onItemSwipeListener;
//    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        initonItemSwipeListener();

        initView();
    }

    public void initView(){
//        MultyItemBean bean=new MultyItemBean();
//        for (int i=0;i<10;i++){
//            bean.setName("mignzhi "+i);
//            data.add(bean);
//        }
        mquickAdapter=new QuickAdapter(this,data);
        mquickAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mquickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mCurrentCounter = mquickAdapter.getData().size();

//        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mquickAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
////        // 开启拖拽
////        mAdapter.enableDragItem(itemTouchHelper, R.id.textView, true);
////        mAdapter.setOnItemDragListener(onItemDragListener);
//
//// 开启滑动删除
//        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
//        mquickAdapter.enableSwipeItem();
//        mquickAdapter.setOnItemSwipeListener(onItemSwipeListener);
////        mAdapter.enableDragItem(mItemTouchHelper);
//        mquickAdapter.setOnItemDragListener(listener);

        mRecyclerView.setAdapter(mquickAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(MainActivity.this, data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

        });
        mquickAdapter.setOnDelListener(new QuickAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
           Toast.makeText(MainActivity.this, "点击了删除", Toast.LENGTH_SHORT).show();

            }
        });


    }



    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        if (mquickAdapter.getData().size() < PAGE_SIZE) {
            mquickAdapter.loadMoreEnd(true);
        } else {
            if (mCurrentCounter >= TOTAL_COUNTER) {
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
                mquickAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                  //  mquickAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                    for (int i=0;i<5;i++){
                        MultyItemBean bean=new MultyItemBean();
                        bean.setName("load "+i);
//                        data.add(bean);
                        mquickAdapter.addData(bean);
                    }

                    mCurrentCounter = mquickAdapter.getData().size();
                    mquickAdapter.loadMoreComplete();
                } else {
                    isErr = true;
                    Toast.makeText(MainActivity.this, "Simulation network error", Toast.LENGTH_LONG).show();
                    mquickAdapter.loadMoreFail();
                }

            }
        }
        mSwipeRefreshLayout.setEnabled(true);

    }

    @Override
    public void onRefresh() {
        mquickAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mquickAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                data.clear();
                for (int i=0;i<10;i++){
                    MultyItemBean bean=new MultyItemBean();
                    bean.setName("refresh "+i);
                    data.add(bean);
                }
                mquickAdapter.setNewData(data);
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
                mquickAdapter.setEnableLoadMore(true);
            }
        },delayMillis);
    }

}
