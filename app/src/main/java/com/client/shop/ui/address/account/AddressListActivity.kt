package com.client.shop.ui.address.account

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.client.shop.R
import com.client.shop.ShopApplication
import com.client.shop.ui.address.base.BaseAddressListActivity
import com.client.shop.ui.address.account.di.AddressModule
import com.client.shop.getaway.entity.Address
import com.client.shop.ui.const.RequestCode
import com.client.shop.ui.address.base.adapter.AddressListAdapter
import com.client.shop.ui.address.base.contract.AddressListPresenter
import com.client.shop.ui.address.base.contract.AddressListView

class AddressListActivity : BaseAddressListActivity<AddressListAdapter, AddressListView, AddressListPresenter<AddressListView>>() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, AddressListActivity::class.java)
    }

    //ANDROID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(getString(R.string.shipping_address))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val isAddAddressRequest = requestCode == RequestCode.ADD_SHIPPING_ADDRESS
        val isEditAddressRequest = requestCode == RequestCode.EDIT_SHIPPING_ADDRESS
        val isResultOk = resultCode == Activity.RESULT_OK
        if ((isAddAddressRequest || isEditAddressRequest) && isResultOk) {
            loadData()
        }
    }

    //INIT

    override fun inject() {
        ShopApplication.appComponent.attachAddressComponent().inject(this)
    }

    override fun getAdapter() = AddressListAdapter(dataList, this, this)

    //CALLBACK

    override fun onEditButtonClicked(address: Address) {
        startActivityForResult(
            AddressActivity.getStartIntent(this, address),
            RequestCode.EDIT_SHIPPING_ADDRESS
        )
    }

    override fun onClick(v: View?) {
        startActivityForResult(
            AddressActivity.getStartIntent(this), RequestCode.ADD_SHIPPING_ADDRESS
        )
    }
}