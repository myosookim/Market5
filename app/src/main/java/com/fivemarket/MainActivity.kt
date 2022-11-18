package com.fivemarket

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fivemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 (액션바 비활성화했기 때문에, 커스텀한 툴바를 액션바 대신 사용)
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
    private fun getForegroundFragment(): Fragment? {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

     */

}