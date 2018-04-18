package com.example.andy.bugapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.*
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.util.Log
import android.widget.Toast

class BluetoothLEManager(context : Context) {
    private val TAG = "BluetoothLEManager"

    private val SCAN_PERIOD = 200

    private val context = context
    private val mHandler : Handler = Handler()

    private lateinit var mManager : BluetoothManager
    private lateinit var mAdapter : BluetoothAdapter
    private lateinit var mScanner : BluetoothLeScanner
    private lateinit var mDevice : BluetoothDevice

    fun isCompatible() : Boolean {
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.d(TAG, "BLE Supported")
            return true
        } else {
            Log.e(TAG, "BLE Not Supported")
            Toast.makeText(context, "BLE Not Supported", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun setManager() {
        mManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    fun getManager() : BluetoothManager {
        Log.d(TAG, "getManager")
        return mManager
    }

    fun setAdapter() {
        mAdapter = BluetoothAdapter.getDefaultAdapter()
    }

    fun getAdapter() : BluetoothAdapter {
        Log.d(TAG, "getAdapter")
        return mAdapter
    }

    fun setScanner() {
        mScanner = mAdapter.bluetoothLeScanner
    }

    fun getScanner() : BluetoothLeScanner {
        Log.d(TAG, "getScanner")
        return mScanner
    }

    fun startScan(enable : Boolean) {
        if (enable) {
            val settings = ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .build()
            val filters = ArrayList<ScanFilter>()
            mHandler.postDelayed(Runnable {
                mAdapter.bluetoothLeScanner.stopScan(mScanCallback)
            }, SCAN_PERIOD.toLong())

            mAdapter.bluetoothLeScanner.startScan(filters, settings, mScanCallback)
            //mScanner.startScan(filters, settings, mScanCallback)
        } else {
            //stopScan()
        }
    }

    fun stopScan() {
        mScanner.stopScan(mScanCallback)
    }

    private val mScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            mDevice = result!!.device
            Log.d(TAG, "onScanResult")
            Log.d(TAG, result.device.name)
            Log.d(TAG, result.device.address)
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            Log.d(TAG, "onBatchScanResults")
            Log.d(TAG, results.toString())
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.e(TAG, "onScanFailes error")
        }
    }
}
