/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zein.apps.juve7.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zein.apps.juve7.Client.ApiClient;
import zein.apps.juve7.Client.ApiInterface;
import zein.apps.juve7.DetailActivity;
import zein.apps.juve7.Model.Itemlist;
import zein.apps.juve7.Model.Responselist;
import zein.apps.juve7.Model.Resultlist;
import zein.apps.juve7.R;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragmentCLub extends Fragment {
    private FloatingActionButton fab_prev, fab_next;
    int curr_page;
    ApiInterface apiInterface;
    List<Itemlist> listdata;
    private ProgressBar spinner;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        spinner = (ProgressBar)view.findViewById(R.id.progressbarkategori);
        spinner.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        fab_prev = (FloatingActionButton)view.findViewById(R.id.fab_prev);
        fab_next = (FloatingActionButton)view.findViewById(R.id.fab_next);
        fab_prev.setVisibility(View.INVISIBLE);
        fab_next.setVisibility(View.INVISIBLE);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Responselist> call = apiInterface.getListIndexClub(getResources().getString(R.string.club_idname));
        call.enqueue(new Callback<Responselist>() {
            @Override
            public void onResponse(Call<Responselist> call, Response<Responselist> response) {
                Resultlist dataResult = response.body().getDataResult();
                listdata = dataResult.getData();
                listRecycler(recyclerView, listdata);
                curr_page = dataResult.getCurrent_page();
                if(dataResult.getNext_page_url() != null){fab_next.setVisibility(View.VISIBLE);}
                if(dataResult.getPrev_page_url() != null){fab_prev.setVisibility(View.VISIBLE);}
                if(curr_page != 1){
                    Toast.makeText(getContext(), "Halaman: "+curr_page, Toast.LENGTH_SHORT).show();
                }
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Responselist> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                showAlertDialog(getContext(),"Maaf!, Terjadi gangguan koneksi. Silahkan Coba kembali!");
            }
        });

        fab_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                if(curr_page > 1){
                    int page = curr_page - 1;
                    getPage(page, recyclerView);
                }
            }
        });
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                int page = curr_page + 1;
                getPage(page, recyclerView);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(curr_page == 1){
                    getPage(curr_page, recyclerView);
                }else{
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        return view;
    }

    private void getPage(int page, final RecyclerView recyclerView){
        Call<Responselist> call = apiInterface.getListIndexClub(getResources().getString(R.string.club_idname),page);
        call.enqueue(new Callback<Responselist>() {
            @Override
            public void onResponse(Call<Responselist> call, Response<Responselist> response) {
                Resultlist dataResult = response.body().getDataResult();
                listdata = dataResult.getData();
                listRecycler(recyclerView, listdata);
                curr_page = dataResult.getCurrent_page();
                if(dataResult.getNext_page_url() != null){fab_next.setVisibility(View.VISIBLE);}else{fab_next.setVisibility(View.GONE);}
                if(dataResult.getPrev_page_url() != null){fab_prev.setVisibility(View.VISIBLE);}else{fab_prev.setVisibility(View.GONE);}
                if(curr_page != 1){
                    Toast.makeText(getContext(), "Halaman: "+curr_page, Toast.LENGTH_SHORT).show();
                }

                spinner.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Responselist> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                showAlertDialog(getContext(),"Maaf!, Terjadi gangguan koneksi. Silahkan Coba kembali!");

            }
        });
    }

    private void listRecycler(RecyclerView recyclerView, final List<Itemlist> data){

        recyclerView.setAdapter(new ContentAdapter(data, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Context context = v.getContext();
                Intent in = new Intent(getContext(), DetailActivity.class);

                in.putExtra("time", data.get(position).getTime());

                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

//                }
//            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.

        private List<Itemlist> data;
        private Context context;


        public ContentAdapter(List<Itemlist> data, Context context) {
            this.data = data;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(data.get(position).getTitle());
            holder.description.setText(data.get(position).getWaktu().replace("&nbsp;","").replace("&minus;",""));
            if(!data.get(position).getImg_tumb().equals("")){
                Picasso.with(context).load(data.get(position).getImg_tumb()).resize(200, 200).centerInside() .placeholder(R.mipmap.ic_launcher).into(holder.avator);
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    public interface ClickListener{
        void onClick(View v, int position);
        void onLongClick(View v, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recycler, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycler.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recycler.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void showAlertDialog(Context context, String message){
        //Deklarasi variabel dengan tipe AlertDialog
        AlertDialog alertDialog;

        //Deklarasi variabel untuk membangun AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        //Membangun dialog
        alertDialogBuilder
                //Menentukan judul dialog
                .setTitle("Peringatan")

                //Mengatur agar dialog dapat dibatalkan
                .setCancelable(true)

                //Menentukan isi pesan pada dialog
                .setMessage(message)

                //Menampilkan tombol Positive
                .setPositiveButton("Ya", null)


        //Tutup pembangunan dialog
        ;

        // Mengeset dialog
        alertDialog = alertDialogBuilder.create();

        //Menampilkan dialog
        alertDialog.show();
    }
}