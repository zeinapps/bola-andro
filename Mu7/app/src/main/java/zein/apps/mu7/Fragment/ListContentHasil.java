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

package zein.apps.mu7.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zein.apps.mu7.Client.ApiClient;
import zein.apps.mu7.Client.ApiInterface;
import zein.apps.mu7.Model.Itemjadwal;
import zein.apps.mu7.Model.Responsejadwal;
import zein.apps.mu7.R;
import zein.apps.mu7.custom.DividerItemDecoration;
import zein.apps.mu7.Model.Itemjadwal;

/**
 * Provides UI for the view with List.
 */
public class ListContentHasil extends Fragment {
    private FloatingActionButton fab_prev, fab_next;
    ApiInterface apiInterface;
    List<Itemjadwal> listdata;
    private ProgressBar spinner;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        spinner = (ProgressBar)view.findViewById(R.id.progressbarkategori);
        spinner.setVisibility(View.VISIBLE);
        fab_prev = (FloatingActionButton)view.findViewById(R.id.fab_prev);
        fab_next = (FloatingActionButton)view.findViewById(R.id.fab_next);
        fab_prev.setVisibility(View.INVISIBLE);
        fab_next.setVisibility(View.INVISIBLE);

        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                            mSwipeRefreshLayout.setRefreshing(false);

                    }
                });

        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Responsejadwal> call = apiInterface.getDataHasil(getResources().getString(R.string.tag));
        call.enqueue(new Callback<Responsejadwal>() {
            @Override
            public void onResponse(Call<Responsejadwal> call, Response<Responsejadwal> response) {
                listdata = response.body().getData();

                listRecycler(recyclerView, listdata);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Responsejadwal> call, Throwable t) {
//                Log.e(TAG, t.toString());

                spinner.setVisibility(View.GONE);
                showAlertDialog(getContext(),"Maaf!, Terjadi gangguan koneksi. Periksa kembali koneksi internet Anda!");
            }
        });

        return view;
    }

    private void listRecycler(RecyclerView recyclerView, final List<Itemjadwal> data){
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ContentAdapter(data, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_home, img_away;
        public TextView skor_home, skor_away ;
        public TextView tim_home, tim_away;
        public TextView waktu, vs;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_jadwal, parent, false));
            img_home = (ImageView) itemView.findViewById(R.id.img_home);
            img_away = (ImageView) itemView.findViewById(R.id.img_away);
            skor_home = (TextView) itemView.findViewById(R.id.skor_home);
            skor_away = (TextView) itemView.findViewById(R.id.skor_away);
            tim_home = (TextView) itemView.findViewById(R.id.tim_home);
            tim_away = (TextView) itemView.findViewById(R.id.tim_away);
            waktu = (TextView) itemView.findViewById(R.id.jadwal_waktu);
            vs = (TextView) itemView.findViewById(R.id.vs);
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

        private List<Itemjadwal> data;
        private Context context;


        public ContentAdapter(List<Itemjadwal> data, Context context) {
            this.data = data;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder( ViewHolder holder, int position) {
            holder.waktu.setText(data.get(position).getWaktu());
            holder.vs.setText("VS");
            if(data.get(position).getIskandang().equals("1")){
                holder.tim_home.setText(context.getResources().getString(R.string.club_name));
                holder.tim_home.setTypeface(null, Typeface.BOLD);
                holder.tim_away.setTypeface(null, Typeface.NORMAL);
                holder.tim_away.setText(data.get(position).getLawan());
                holder.skor_home.setText(data.get(position).getSkortim());
                holder.skor_away.setText(data.get(position).getSkorlawan());
//                if(!data.get(position).getUrlimgtim().equals("")){
//                    Picasso.with(context).load(data.get(position).getUrlimgtim()).resize(200, 200).centerInside() .placeholder(R.mipmap.ic_launcher).into(holder.img_home);
//                }
//                if(!data.get(position).getUrlimglawan().equals("")){
//                    Picasso.with(context).load(data.get(position).getUrlimglawan()).resize(200, 200).centerInside() .placeholder(R.mipmap.ic_launcher).into(holder.img_away);
//                }
            }else{
                holder.tim_home.setText(data.get(position).getLawan());
                holder.tim_away.setText(context.getResources().getString(R.string.club_name));
                holder.tim_away.setTypeface(null, Typeface.BOLD);
                holder.tim_home.setTypeface(null, Typeface.NORMAL);
                holder.skor_home.setText(data.get(position).getSkorlawan());
                holder.skor_away.setText(data.get(position).getSkortim());
//                if(!data.get(position).getUrlimgtim().equals("")){
//                    Picasso.with(context).load(data.get(position).getUrlimglawan()).resize(200, 200).centerInside() .placeholder(R.mipmap.ic_launcher).into(holder.img_home);
//                }
//                if(!data.get(position).getUrlimglawan().equals("")){
//                    Picasso.with(context).load(data.get(position).getUrlimgtim()).resize(200, 200).centerInside() .placeholder(R.mipmap.ic_launcher).into(holder.img_away);
//                }
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
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