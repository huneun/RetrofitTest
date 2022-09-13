package com.example.retrofitconnection

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import com.example.retrofitconnection.databinding.FragmentGsonBinding
import com.example.retrofitconnection.gson.RetrofitManager
import com.example.retrofitconnection.utils.Constants.TAG
import com.example.retrofitconnection.utils.RESPONSE_STATE

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GsonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GsonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentGsonBinding.inflate(inflater, container, false)


        view.btnSearch.setOnClickListener{
            RetrofitManager.instance.searchPhotos(searchTerm = view.etSearch.text.toString(), completion = {
                responseState, value ->
                when(responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api 호출 성공 : $value")
                    }
                    RESPONSE_STATE.FAIL -> {
                        Log.d(TAG, "api 호출 실패 : $value")
                    }
                }
            })
        }

        return view.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GsonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GsonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}