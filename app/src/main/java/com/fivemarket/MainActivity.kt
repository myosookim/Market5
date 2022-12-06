package com.fivemarket

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.fivemarket.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var mDbRef : DatabaseReference //데이터 베이스 객체 초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var keyHash = Utility.getKeyHash(this)
        // 하단바
        val navcontroller = binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        binding.bottomNavigationView.setupWithNavController(navcontroller)
        // 백스택을 자동으로 저장하고 복구하는 기능 삭제
        binding.bottomNavigationView.apply {
            setupWithNavController(navcontroller)
            setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, navcontroller)
                navcontroller.popBackStack(item.itemId, inclusive = false)
                true
            }
        }

        // Use the navigate() method that takes a navOptions DSL Builder
        // 툴바 (액션바 비활성화했기 때문에, 커스텀한 툴바를 액션바 대신 사용).
        // 주의!! : 툴바 코드는 onCreate의 제일 마지막 부분에 있어야 합니다.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 앱바 제어를 위한 툴바 접근
        val actionBar: ActionBar? = supportActionBar
        // 앱바에 뒤로가기 버튼 생성
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }



    // 액션바에 메뉴를 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.acb_menu->{
                binding.fragmentContainerView.findNavController().navigate(R.id.pageMenuFragment)
            }
            R.id.acb_chatting->{
                binding.fragmentContainerView.findNavController().navigate(R.id.chatLogoinActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 프래그먼트 이동
    /*
    fun replacefragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().
            replace(binding.fragmentContainerView.id, fragment).commit()
    }

     */

    /*
    fun getForegroundFragment(): Fragment? {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_main)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

     */

}