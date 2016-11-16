package com.qgz.qinsa.cbk.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qgz.qinsa.cbk.R;
import com.qgz.qinsa.cbk.adapters.ListViewAdapter;
import com.qgz.qinsa.cbk.bean.NewsEntity;
import com.qgz.qinsa.cbk.ui.DetailsActivity;
import com.qgz.qinsa.cbk.url.Urls;
import com.qgz.qinsa.cbk.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements ListView.OnItemClickListener{

    private ListView mListView;
    private ListViewAdapter listViewAdapter;
    private List<NewsEntity> mList = new ArrayList<>();
    private Handler mHandler = new Handler();
    private int page;


    public static ContentFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page",page);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        page = bundle.getInt("page");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        // Inflate the layout for this fragment

//        Bundle bundle = getArguments();
//        String title = "头条";
//        if (bundle != null) {
//            title = bundle.getString("titles");
//        }


        mListView = (ListView) view.findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(getContext(),mHandler);
        mListView.setAdapter(listViewAdapter);

        switch (page) {
            case 0:
                //头条页
                String toutaioUrl = Urls.HEADLINE_URL + Urls.HEADLINE_TYPE;
                downloadData(toutaioUrl);
                break;
            case 1:
                //百科页
                String baikeUrl = Urls.BASE_URL+Urls.CYCLOPEDIA_TYPE;
                downloadData(baikeUrl);
                break;
            case 2:
                //资讯页
                String zixunUrl = Urls.BASE_URL+Urls.CONSULT_TYPE;
                downloadData(zixunUrl);
                break;
            case 3:
                //经营页
                String jyUrl = Urls.BASE_URL+Urls.OPERATE_TYPE;
                downloadData(jyUrl);

                break;
            case 4:
                //数据页
                String dataUrl = Urls.BASE_URL+Urls.DATA_TYPE;
                downloadData(dataUrl);
                break;
        }


        mListView.setOnItemClickListener(this);

        return view;
    }

    private void downloadData(final String url) {
//        if (NetWorkUtils.isConnected(getContext())){
//
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                String str = HttpUtils.downloadData(url);
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONArray datas = jsonObject.optJSONArray("data");
                    for (int i = 0; i < datas.length(); i++) {
                        NewsEntity newsEntity = new NewsEntity(datas.optJSONObject(i));
                        mList.add(newsEntity);

                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //放了数据就刷新适配器
                            listViewAdapter.addAll(mList);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        NewsEntity newsEntity = listViewAdapter.getEntity(i);
        int id = newsEntity.getId();
        Log.d("TAG", "onItemClick: "+id);
        String url=String.format(Urls.CONTENT_URL,id);
        Log.d("TAG", "onItemClick: "+url);

        String s = HttpUtils.downloadData(url);
        Log.d("TAG", "onItemClick: "+s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject data = jsonObject.optJSONObject("data");
            String wap_content= data.optString("wap_content");
            intent.putExtra("wap_content",wap_content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        intent.putExtra("mList",newsEntity);//传实体类就要实现实例化
        startActivity(intent);

    }
}
