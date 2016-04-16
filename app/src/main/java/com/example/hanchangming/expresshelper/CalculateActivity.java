package com.example.hanchangming.expresshelper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class CalculateActivity extends AppCompatActivity {

    EditText weightEditText, heightEditText, widthEditText, lengthEditText;
    Spinner destinationProvinceSpinner, destinationCitySpinner;
    String destinationProvinceSelectedItem, destinationProvinceSelected, destinationCitySelected, localProvince, localCity;
    LocationManager locationManager;
    double latitude, longitude, weight, height, width, length;
    Button okButton;
    final String baiduKey = "V9otziEffEGKneZkA8YaSoRF";
    TextView localShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        weightEditText = (EditText) findViewById(R.id.weight);
        heightEditText = (EditText) findViewById(R.id.height);
        widthEditText = (EditText) findViewById(R.id.width);
        lengthEditText = (EditText) findViewById(R.id.length);
        destinationProvinceSpinner = (Spinner) findViewById(R.id.destinationProvince);
        destinationCitySpinner = (Spinner) findViewById(R.id.destinationCity);
        okButton = (Button) findViewById(R.id.okButton);
        localShow = (TextView)findViewById(R.id.localShow);

        destinationProvinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destinationProvinceSelectedItem = destinationProvinceSpinner.getSelectedItem().toString();
                updateAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputInformation();
            }
        });
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                getLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    protected void updateAdapter() {
        if (destinationProvinceSelectedItem.equals("直辖市")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destinationMunicipalityList, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("河北省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_HeBeiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("山西省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_ShanXiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("内蒙古自治区")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_Inner_Mongolia_Autonomous_Region, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("辽宁省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_LiaoNingProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("吉林省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_JiLinProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("黑龙江省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_HeiLongJiangProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("江苏省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_JiangSuProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("浙江省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_ZheJiangProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("安徽省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_AnHuiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("福建省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_FuJianProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("江西省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_JiangXiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("山东省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_ShanDongProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("河南省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_HeNanProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("湖北省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_HuBeiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("湖南省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_HuNanProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("广东省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_GuangDongProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("广西壮族自治区")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_Guangxi_Zhuang_Autonomous_Region, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("海南省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_HaiNanProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("四川省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_SiChuanProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("贵州省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_GuiZhouProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("云南省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_YunNanProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("西藏自治区")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_Tibet_Autonomous_Region, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("陕西省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_ShanXiiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("甘肃省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_GanSuProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("青海省")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_QingHaiProvince, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("宁夏回族自治区")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_Ningxia_Hui_Autonomous_Region, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }
        if (destinationProvinceSelectedItem.equals("新疆维吾尔族自治区")) {
            ArrayAdapter<CharSequence> destinationCityAdapter = ArrayAdapter.createFromResource(CalculateActivity.this, R.array.destination_Xinjiang_Uygur_Autonomous_Region, R.layout.support_simple_spinner_dropdown_item);
            destinationCitySpinner.setAdapter(destinationCityAdapter);
        }

    }


    protected void getInputInformation() {

        if (weightEditText.length() < 1 || heightEditText.length() < 1 || widthEditText.length() < 1 || lengthEditText.length() < 1) {
            Toast.makeText(this, "请输入数值", Toast.LENGTH_SHORT).show();
        }

        if (weightEditText.length() >= 1 && heightEditText.length() >= 1 && widthEditText.length() >= 1 && lengthEditText.length() >= 1) {
            weight = Double.parseDouble(weightEditText.getText().toString());
            height = Double.parseDouble(heightEditText.getText().toString());
            width = Double.parseDouble(widthEditText.getText().toString());
            length = Double.parseDouble(lengthEditText.getText().toString());
            destinationProvinceSelected = destinationProvinceSpinner.getSelectedItem().toString();
            destinationCitySelected = destinationCitySpinner.getSelectedItem().toString();
            Log.v(PRINT_SERVICE, weight + "|" + height + "|" + width + "|" + length + "|" + destinationProvinceSelected + "|" + destinationCitySelected);
            Bundle data = new Bundle();
            data.putDouble("weight",weight);
            data.putDouble("height",height);
            data.putDouble("width",width);
            data.putDouble("length",length);
            data.putString("destinationProvinceSelected",destinationProvinceSelected);
            data.putString("destinationCitySelected",destinationCitySelected);
            Intent intent = new Intent(CalculateActivity.this, listActivity.class);
            intent.putExtras(data);
            startActivity(intent);
        }
    }

    protected void getLocation(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Geocoding();
            Toast.makeText(this, "成功获取位置信息!", Toast.LENGTH_SHORT).show();
            Log.v(PRINT_SERVICE, latitude + "|" + longitude);
        }
    }

    protected void Geocoding() {
        String adressString = "http://api.map.baidu.com/geocoder/v2/?ak=" + baiduKey + "&location=" + latitude + "," + longitude + "&output=json";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(adressString, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    localProvince = response.getJSONObject("result").getJSONObject("addressComponent").getString("province");
                    localCity = response.getJSONObject("result").getJSONObject("addressComponent").getString("city");
                    Log.v(PRINT_SERVICE, localProvince + "|" + localCity);
                    localShow.setText(localProvince+" "+localCity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(PRINT_SERVICE, error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
