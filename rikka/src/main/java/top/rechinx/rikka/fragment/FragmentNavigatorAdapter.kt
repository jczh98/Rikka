package top.rechinx.rikka.fragment

import androidx.fragment.app.Fragment

interface FragmentNavigatorAdapter {

    val count: Int

    fun onCreateFragment(position: Int): Fragment

    fun getTag(position: Int): String
}
