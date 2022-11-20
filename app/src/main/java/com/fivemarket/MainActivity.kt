package com.fivemarket

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.fivemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val items = arrayListOf(
        Items("상품1","업체1",5000,Etype.SILK,R.drawable.main_itemimg1),
        Items("상품2","업체2",6000,Etype.COTTON,R.drawable.main_itemimg2),
        Items("상품3","업체3",7000,Etype.LEATHER,R.drawable.main_itemimg3),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3)
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var items_silk : ArrayList<Items> = arrayListOf()
        var items_cotton : ArrayList<Items> = arrayListOf()
        var items_leather : ArrayList<Items> = arrayListOf()
        var items_lace : ArrayList<Items> = arrayListOf()

        for(i in items){
            if(i.type == Etype.SILK) {
                items_silk.add(i)
            }
            if(i.type == Etype.COTTON){
                items_cotton.add(i)
            }
            if(i.type == Etype.LEATHER){
                items_leather.add(i)
            }
            if(i.type == Etype.LACE){
                items_lace.add(i)
            }
        }
        intent.putExtra("total_items", items)
        intent.putExtra("silk_items", items_silk)
        intent.putExtra("cotton_items", items_cotton)
        intent.putExtra("leather_items", items_leather)
        intent.putExtra("lace_items", items_lace)

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