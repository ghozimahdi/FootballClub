package com.module.footballclub.ui.schedules

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.module.footballclub.adapter.AdapterFootballClub
import com.module.footballclub.model.EventsItem
import com.module.footballclub.ui.matchdetail.MatchDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SchedulesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SchedulesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SchedulesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    var adapterEvent: AdapterFootballClub? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        adapterEvent = AdapterFootballClub(activity!!)
        return FragmentUi<Fragment>(adapterEvent!!).createView(AnkoContext.create(ctx, this))
    }

    class FragmentUi<T>(var listAdapter: AdapterFootballClub) : AnkoComponent<T> {
        override fun createView(ui: AnkoContext<T>) = with(ui) {
            verticalLayout {
                recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = listAdapter
                    listAdapter.setOnClickListener(object : AdapterFootballClub.OnClickItems {
                        override fun onClick(footballClub: EventsItem, position: Int) {
                            startActivity<MatchDetailActivity>("data" to footballClub)
                        }
                    })
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun changeData(data: MutableList<EventsItem>) {
        adapterEvent?.run {
            adapterEvent?.changeData(data)
            notifyDataSetChanged()
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SchedulesFragment.
         */

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
                SchedulesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, param1)
                    }
                }
    }
}

