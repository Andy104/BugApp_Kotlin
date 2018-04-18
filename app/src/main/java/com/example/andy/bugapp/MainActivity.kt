package com.example.andy.bugapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.bluetooth.BluetoothAdapter
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val REQUEST_ENABLE_BT = 1

    private val mBleManager : BluetoothLEManager = BluetoothLEManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val fragment = BleFragment.newInstance()
        viewFragment(fragment)

        if (mBleManager.isCompatible()) {
            mBleManager.setManager()
            mBleManager.setAdapter()
        } else {
            Toast.makeText(this, "Bluetooth not compatible with BLE", Toast.LENGTH_SHORT).show()
        }

        if (!mBleManager.getAdapter().isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        try {
            Log.d(TAG, "scanner")
            mBleManager.setScanner()
            mBleManager.stopScan()
            Log.d(TAG, "scanner start")
            mBleManager.startScan(true)
            Log.d(TAG, "scanner stop")
        } catch (e : Exception) {
            Log.e(TAG, e.toString())
        }
    }

    private val navigationListener = object : BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.ble_button -> {
                    viewFragment(BleFragment.newInstance())
                    return true
                }
                R.id.uart_button -> {
                    viewFragment(UartFragment.newInstance())
                    return true
                }
                R.id.info_button -> {
                    viewFragment(InfoFragment.newInstance())
                    return true
                }
            }
            return false
        }
    }

    private fun viewFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit()
        toolbar_title.text = fragment.arguments.getString("title")
    }
}
