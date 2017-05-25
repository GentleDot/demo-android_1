package net.gentledot.helloandroid;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OptionMenu extends AppCompatActivity {
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        View view = menu.findItem(R.id.menu_search).getActionView();

        if(view != null){
            editText = (EditText) view.findViewById(R.id.searchEditText);

            if (editText != null){
                editText.setOnEditorActionListener(onSearchListener);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴 클릭됨", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴 클릭됨", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴 클릭됨", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // 텍스트뷰에 키 입력이 끝날 때 이벤트 설정
    TextView.OnEditorActionListener onSearchListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(event == null || event.getAction() == KeyEvent.ACTION_UP){
                // 검색 메소드 호출
                search();

                // 키패드 닫기기
               InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

            return true;
        }
    };

    // 검색 메소드
    private void search() {
        String searchString = editText.getEditableText().toString();
        Toast.makeText(this, "검색한 문자열 : " + searchString, Toast.LENGTH_LONG).show();
    }


}
