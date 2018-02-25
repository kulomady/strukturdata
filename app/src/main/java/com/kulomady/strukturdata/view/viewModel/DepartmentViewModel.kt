package com.kulomady.strukturdata.view.viewModel

import java.io.Serializable

/**
 * @author kulomady on 2/25/18.
 */

data class DepartmentViewModel(
        val id:String,
        val name:String
) : Serializable {
    override fun toString(): String {
        return name;
    }
}