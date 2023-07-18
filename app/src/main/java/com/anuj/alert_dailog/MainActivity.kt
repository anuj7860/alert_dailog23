package com.anuj.alert_dailog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.os.Parcel
import android.os.Parcelable

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText

@Suppress("NAME_SHADOWING")
class MainActivity() : AppCompatActivity(), Parcelable {
            private lateinit var btnShow:Button
            private lateinit var tv0: TextView
            private var num=0
            private lateinit var btnWithItem : Button
            private lateinit var btnWithMItem : Button
            private var items= arrayOf("dog","cat","rat")
            private var booleanItems= booleanArrayOf(false,false,false)

    constructor(parcel: Parcel) : this() {
        num = parcel.readInt()
        items = parcel.createStringArray() as Array<String>
        booleanItems = parcel.createBooleanArray()!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                btnShow = findViewById(R.id.btn_show)
                tv0 = findViewById(R.id.tv_zero)
                tv0.text = num.toString()
                btnWithItem = findViewById(R.id.btn_c)
                btnWithMItem = findViewById(R.id.btn_m)
                btnShow.setOnClickListener {
                    AlertDialog.Builder(this)
                        .setTitle("Delete")
                        .setMessage("click for show the value")
                        .setCancelable(false)
                        .setPositiveButton("Positive") { _, _ ->
                            num += 2
                            tv0.text = num.toString()
                            makeText(this, "Positive click", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Negative") { _, _ ->
                            num -= 2
                            tv0.text = num.toString()
                            makeText(this, "Negative click", Toast.LENGTH_SHORT).show()
                        }
                        .setNeutralButton("Neutral") { _, _ ->
                            num = 0
                            tv0.text = num.toString()
                            makeText(this, "Neutral click", Toast.LENGTH_SHORT).show()
                        }
                        .show()
                }
                btnWithItem.setOnClickListener {
                    AlertDialog.Builder(this)
                        .setTitle("Remove")
                        .setItems(items) { _, position ->
                            makeText(
                                this,
                                "selected item ${items[position]}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .setPositiveButton("positive") { _, _ ->
                            makeText(this, "Positive click", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Negative") { _, _ ->
                            makeText(this, "Negative click", Toast.LENGTH_SHORT).show()
                        }
                        .setNeutralButton("Neutral") { _, _ ->
                            makeText(this, "Neutral click", Toast.LENGTH_SHORT).show()
                        }
                        .show()


                }
                btnWithMItem.setOnClickListener {
                    AlertDialog.Builder(this)
                        .setTitle("ITEMS")
                        .setMultiChoiceItems(items, booleanItems) { _, position, isChecked ->
                            println("select position $position isChecked $isChecked")
                            for (position in booleanItems.indices) {
                                if (booleanItems[position]) {
                                    println("checked item ${items[position]}")
                                }

                            }
                            makeText(this, "selected $position $isChecked ", Toast.LENGTH_SHORT)
                                .show() 
                        }
                        .setPositiveButton("positive") { _, _ ->
                            println("boolean array $booleanItems $items")
                            makeText(this, "Positive Clicked", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Negative") { _, _ ->
                            makeText(this, "Negative Clicked", Toast.LENGTH_SHORT).show()
                        }
                        .setNeutralButton("Neutral") { _, _ ->
                            makeText(this, "Neutral Clicked", Toast.LENGTH_SHORT).show()
                        }
                        .show() 
                }
            }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(num)
        parcel.writeStringArray(items)
        parcel.writeBooleanArray(booleanItems)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
    
