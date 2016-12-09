package andara.heatcliff.yuclean;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailKegiatanActivity extends AppCompatActivity {

    TextView txtTanggal, txtJudul, txtDeskripsi;
    ImageView imgKegiatan;
    Button btnShare, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan);

        txtTanggal = (TextView) findViewById(R.id.txtTanggal);
        txtJudul = (TextView) findViewById(R.id.txtJudul);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        imgKegiatan = (ImageView) findViewById(R.id.imgKegiatan);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnRead = (Button) findViewById(R.id.btnRead);

        txtTanggal.setText(getIntent().getExtras().get("tanggal").toString());
        txtJudul.setText(getIntent().getExtras().get("judul").toString());
        txtDeskripsi.setText(getIntent().getExtras().get("deskripsi").toString());

        Glide.with(this).load("http://yuclean.andara-tech.com/images/event/"
                + getIntent().getExtras().get("gambar").toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo_yuclean_bgwhite)
                .into(imgKegiatan);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserInternet = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://yuclean.andara-tech.com/event/" + getIntent().getExtras().get("url").toString()));
                view.getContext().startActivity(browserInternet);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Bagikan", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plan");
//                    i.setType("image/png");
                i.putExtra(Intent.EXTRA_SUBJECT, "YuClean");
                i.putExtra(Intent.EXTRA_TEXT, "Yuk ikuti acara " + getIntent().getExtras().get("judul").toString()
                        + "\n buka laman " + "http://yuclean.andara-tech.com/event/" + getIntent().getExtras().get("url").toString());
//                    i.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://entertaiment.emaka.planetku/drawable/ic_icon_planetku"));
//                    i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("ikon planet")));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent share = Intent.createChooser(i, "Bagikan YuClean");
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(share);
            }
        });
    }
}
