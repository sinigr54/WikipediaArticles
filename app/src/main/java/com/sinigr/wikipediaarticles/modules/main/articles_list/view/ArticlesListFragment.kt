package com.sinigr.wikipediaarticles.modules.main.articles_list.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinigr.wikipediaarticles.R
import com.sinigr.wikipediaarticles.base.BaseFragment
import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.modules.main.articles_list.presenter.IArticlesListPresenter
import com.sinigr.wikipediaarticles.modules.main.articles_list.view.adapter.ArticlesAdapter
import kotlinx.android.synthetic.main.fragment_list_articles.*
import org.koin.android.ext.android.inject

class ArticlesListFragment : BaseFragment(), IArticlesListView {

    companion object {
        private const val TAG = "ArticlesListFragment"

        private const val LOCATION_REQUEST_CODE = 1
    }

    private val presenter: IArticlesListPresenter by inject()

    private lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ArticlesAdapter(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        articlesRecyclerView.adapter = adapter
        articlesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        checkLocationPermissionsAndLoadArticles()
    }

    private fun loadArticles() {
        presenter.loadArticles()
    }

    override fun onArticlesLoaded(articles: List<ArticleEntity>) {
        adapter.setData(articles)
    }

    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun checkLocationPermissionsAndLoadArticles() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED

        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_REQUEST_CODE
            )
        } else {
            loadArticles()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                loadArticles()
            } else {
                checkLocationPermissionsAndLoadArticles()
            }
        }
    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }

}