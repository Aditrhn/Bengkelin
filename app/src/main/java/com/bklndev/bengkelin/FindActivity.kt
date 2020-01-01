package com.bklndev.bengkelin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FindActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var gmap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.f_maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap

        gmap.isMyLocationEnabled = true

        val location = arrayOf("Srijoyo Motor",-7.746642, 110.419610)
        val lc_name = location[0] as String
        val lc_lat = location[1] as Double
        val lc_long = location[2] as Double
        val lc_latlong = LatLng(lc_lat, lc_long)
        gmap.addMarker(MarkerOptions().position(lc_latlong).title(lc_name))
        gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(lc_latlong,15f))
        gmap.setMinZoomPreference(14.5f)
        gmap.setMaxZoomPreference(15.5f)
    }
}
