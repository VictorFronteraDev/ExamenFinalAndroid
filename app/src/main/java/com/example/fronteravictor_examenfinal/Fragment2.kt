package com.example.fronteravictor_examenfinal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fronteravictor_examenfinal.connection.Api
import com.example.fronteravictor_examenfinal.connection.Client
import com.example.fronteravictor_examenfinal.model.Player
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Fragment2: Fragment() {


    private var retrofit: Retrofit? = null
    private var playerAdapter: PlayerAdapter? = null
    private var pressedPosition: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.layout_fragment2, container, false)

        val recycler: RecyclerView = view.findViewById(R.id.recycler_fragment)

        val spinner: Spinner = view.findViewById(R.id.spinner)

        val btnAccept: Button = view.findViewById(R.id.btnAccept)

        val txtId: TextView = view.findViewById(R.id.txtid)
        val txtIdJson: TextView = view.findViewById(R.id.txtidElement)

        val txtSurname: TextView = view.findViewById(R.id.txtSurname)

        val txtYears: TextView = view.findViewById(R.id.txtYears)

        recycler.setHasFixedSize(true)

        recycler.addItemDecoration(DividerItemDecoration(context, 1))

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        playerAdapter = PlayerAdapter { btn, pressesPosition ->
            val player = playerAdapter?.getItem(pressesPosition)
            btn.setOnClickListener {
                if (player != null) {
                    deletePlayer(player.id!!)
                }
            }

        }

        playerAdapter!!.setOnClickListener {
            pressedPosition = recycler.getChildLayoutPosition(it)

            val player = playerAdapter?.getItem(pressedPosition)
            val builder = AlertDialog.Builder(requireContext())
            var equipos  = ""
            builder.setTitle(R.string.teams)
            if (player != null) {
                for (team in player.teams) {
                    equipos += team + "\n"
                }
                builder.setMessage(
                    equipos
                )
            }
            builder.setPositiveButton(R.string.accept, null)
            val dialog = builder.create()
            dialog.show()

        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        btnAccept.setOnClickListener {
            if (spinner.selectedItem == getString(R.string.only_name)) {
                txtId.visibility = View.GONE
                txtIdJson.visibility = View.GONE
                txtSurname.visibility = View.GONE
                txtYears.visibility = View.GONE
                playerAdapter?.

            } else {
                txtId.visibility = View.VISIBLE
                txtIdJson.visibility = View.VISIBLE
                txtSurname.visibility = View.VISIBLE
                txtYears.visibility = View.VISIBLE
            }
        }

        recycler.adapter = playerAdapter

        retrofit = Client.getClient()

        getData()

        return view
    }
    private fun getData() {
        val api: Api? = retrofit?.create(Api::class.java)

        api?.getPlayers()?.enqueue(object : Callback<ArrayList<Player>> {
            override fun onResponse(call: Call<ArrayList<Player>>, response: Response<ArrayList<Player>>) {
                if (response.isSuccessful) {
                    val playersList = response.body()

                    if (playersList != null) {
                        playerAdapter?.addToList(playersList)
                    }
                } else
                    Toast.makeText(context, R.string.fail_reponse, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<Player>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deletePlayer(id: Int){
        val api: Api? = retrofit?.create(Api::class.java)

        api?.deletePlayer(id)?.enqueue(object : Callback<Player> {
            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                if (response.isSuccessful) {
                    val player = response.body()

                    if (player != null) {
                        playerAdapter?.deleteFromList(id - 1)
                        Toast.makeText(context, R.string.deleted, Toast.LENGTH_SHORT).show()
                    }
                } else
                    Toast.makeText(context, R.string.fail_reponse, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Player>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}