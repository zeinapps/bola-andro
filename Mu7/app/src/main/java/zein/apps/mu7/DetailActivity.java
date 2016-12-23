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

package zein.apps.mu7;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zein.apps.mu7.Client.ApiClient;
import zein.apps.mu7.Client.ApiInterface;
import zein.apps.mu7.Model.Itemdetil;
import zein.apps.mu7.Model.Responsedetil;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    TextView title, konten, sumber, waktu;
    ImageView img_url;
    ApiInterface apiInterface;
    private GridView horizontalGridview;
    private ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String time = getIntent().getStringExtra("time");
        progressBar = (ProgressBar)findViewById(R.id.progressbardetail);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        konten = (TextView) findViewById(R.id.detail_konten);
        sumber =  (TextView) findViewById(R.id.sumber);
        title =  (TextView) findViewById(R.id.detail_title);
        img_url = (ImageView) findViewById(R.id.image);
        getDetailData(time);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    protected void getDetailData(String time){
        Call<Responsedetil> call = apiInterface.getDetailData(time);
        call.enqueue(new Callback<Responsedetil>() {
            @Override
            public void onResponse(Call<Responsedetil> call, Response<Responsedetil> response) {
                Itemdetil detail = response.body().getDataResultDetail().getDetil();
                CollapsingToolbarLayout collapsingToolbar =
                        (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                collapsingToolbar.setTitle(detail.getTitle());
                konten.setText(detail.getKonten()
                        .replace("â€","\"")
                        .replace("œ","")
                        .replace("Â","")
                        .replace("Saksikan cuplikan pertandingan dari Liga Inggris, La Liga, Liga Champions, dan Liga Europa, dengan kualitas HD di sini",""));
                title.setText(detail.getTitle());
                String html = "<b>Sumber</b> : "+detail.getSumber();
                sumber.setText(Html.fromHtml(html));
                if(!detail.getImg().equals("")){
                    Picasso.with(DetailActivity.this).load(detail.getImg()).into(img_url);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Responsedetil> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DetailActivity.this, "Maaf!, Terjadi gangguan koneksi. Silahkan Coba kembali!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }
}
