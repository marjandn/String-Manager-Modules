package dn.marjan.texteditmodules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for set span programmatically
//        txv_ws.mWordSpacing = 3.2F
//
//        txv_ls.mLetterSpacing = 3.2F
    }
}
