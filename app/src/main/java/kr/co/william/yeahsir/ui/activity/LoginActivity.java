package kr.co.william.yeahsir.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.net.URLDecoder;
import java.net.URLEncoder;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.RSACrypto;

/**
 * Created by sheo on 2018-02-11.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_join).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.bt_login:
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.bt_join:
                intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void test() {
        String planText = URLEncoder.encode("브이피테스트文字123456*&^%$#@+\\ ");

        String privKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqOsMPTU5siAbUogF+FjyLfnEJRrLmYmEnlJx8veQLKSvvPKPdpUruMf4b05MiJbP8vOWY9K5NWGa+qStVuLahPRcu5DpeqyawaEUyozv2cTPd9hODIfU1SR/22HY8UZTtQeBPlLYUdTSWwx7/hId58OfeMLDfD0Kr0Pt3EPPRI6NJXSulz0MJYrvEkuW12o4BZYtw2JGA9chOgNPsriyY0SP5J6KUx4QXhFPfSBS8yg8lGcbccEmOV6+h35DEKoCFi/s27l+tLQ6ae78uiNWILA5T1AT6a0GJCMXoMTHAqIGHZR5IsHzr1nZzbxuacbXi5T119uN34FrMV9njL5cKwIDAQAB";
        String pubKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCo6ww9NTmyIBtSiAX4WPIt+cQlGsuZiYSeUnHy95AspK+88o92lSu4x/hvTkyIls/y85Zj0rk1YZr6pK1W4tqE9Fy7kOl6rJrBoRTKjO/ZxM932E4Mh9TVJH/bYdjxRlO1B4E+UthR1NJbDHv+Eh3nw594wsN8PQqvQ+3cQ89Ejo0ldK6XPQwliu8SS5bXajgFli3DYkYD1yE6A0+yuLJjRI/knopTHhBeEU99IFLzKDyUZxtxwSY5Xr6HfkMQqgIWL+zbuX60tDpp7vy6I1YgsDlPUBPprQYkIxegxMcCogYdlHkiwfOvWdnNvG5pxteLlPXX243fgWsxX2eMvlwrAgMBAAECggEACcS+CnQfiAuPSPCQ19Hc/4eoLjUkfRaU0ssVx+VFMy+68gRdA5CQ3LjDxzwR9PwzvmsT0WYIS+v/GGuZkqK/3Fx9WGOC/RMqR/RWzXflDwTwso+Rw+u/BSx9MGSyNRHKGXf7o20LEYBrQdSw/vpxPEv11vf8chzdl1EoWkqZm+9CN8FaRmToDDylvGr1UKqKGKH4+dvrmf35a0aHbOxmE8Cq24yPiJON5BeQmsX1zDfgLiljjQyIfnY4k/sjI1x80OsfvCZlgNysU5C80bth2ziogfQPvaydeqB7PeXZ+E7/AO63wo1cbot+3x3LTOCAhlElU3Ev1njRHB1sECHCsQKBgQDuVgXm8wK1y96cN0JVPqvjmocQvviDGaOecNJsrMCVDk0Fw5Bha8G8QQSn0goyqmxrc/qs48TPTOK+zkqoewNtct3n/E4GSmPcqwhQV1v5cSOkkll3Wy2ZjZrdZa5KpQZcyNnusu+mri7byriTMsugArq9kYljhWdK1DV3CagU/QKBgQC1b/OS1mcF5KLeeBoGli/Bfl9id1Dw2idkcNgVcyK8deESsV+MHqzP4cfYjIR1PsJGP8tJPVRzXG15SvxGhgFZxx+4daI0yLtHcHnDUeDsHPBuvGJvlrbehaX05x8kASO+81iNUrNu60mylsLZRzSr7cnl1CIfBvneGxFXNl3SRwKBgFBYRYMWlupMQM4uvrjX+MAwnqmhh0tCwGqwI++oyP82o/Z2d9yvo87fqD8eyntMAmKy5WWVHwq3I5UpG/fyCDTETVRgNLlgKIwCSDo1SPctEdPv1iMrZJLoLrXGClx1h1COERGr1qg8/eTwUQqmTAi0AtnXDxayqSCLqz5mzffNAoGBAJkHr70tsP87dFOHoQvlKKpe2DzIvjAnEAqbUuXkkmu6UPffOuOIC+HaYSC4hBAPuvRdYiyH2e7jCkK/iEBDTmhWlt6R+RnkJHGA4HJpYPhFAFWl9rB/wgW1W+BP/+fyPiAUR6ALHOR091f16KoycK6ChUOfxJe+3okC84FHjaKJAoGBAJ9UNnNaClW4aVZ+ldIGrlgnksVAesw4cqGg/64MfKPisZ9nV93DuztRcZOwgI7iED0SdxG66OVSTXcgEMBc/xmduJqNzPgGinEbaGgfVyQJgrLOKw/TnU2lGtpnW0kF8egBPgO2DROWHouCiUh7Aepu6EZVyXhhc2NIqtttqVAu";

//        BufferedReader brPublicKey = null;
//        BufferedReader brPrivateKey = null;
//        try {
//            brPublicKey = new BufferedReader(new FileReader("PublicKey.txt"));
//            sPublicKey2 = brPublicKey.readLine();   // First Line Read
//            brPrivateKey = new BufferedReader(new FileReader("PrivateKey.txt"));
//            sPrivateKey2 = brPrivateKey.readLine(); // First Line Read
//            System.out.println("Pubilc Key & Private Key Read");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (brPublicKey != null)
//                    brPublicKey.close();
//                if (brPrivateKey != null)
//                    brPrivateKey.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        try {
            RSACrypto rsaEncObj = new RSACrypto();
            rsaEncObj.setPriKey(privKey);
            rsaEncObj.setPubKey(pubKey);

            String encStr = rsaEncObj.encrypt(planText, "RSA/ECB/PKCS1Padding", "HEX");
            System.out.println("[sheotest] encStr[" + encStr + "]");
            String d = rsaEncObj.decrypt(encStr, "RSA/ECB/PKCS1Padding", "euc-kr");
            System.out.println("[sheotest] decStr[" + URLDecoder.decode(d) + "]");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
