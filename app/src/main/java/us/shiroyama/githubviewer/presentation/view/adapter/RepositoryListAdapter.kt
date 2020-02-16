package us.shiroyama.githubviewer.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import us.shiroyama.githubviewer.R
import us.shiroyama.githubviewer.presentation.viewmodel.RepositoryViewModel
import javax.inject.Inject

/**
 * [RecyclerView.Adapter] for repository list
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class RepositoryListAdapter @Inject constructor(context: Context, val picasso: Picasso) :
    RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var viewModels: List<RepositoryViewModel>

    init {
        viewModels = emptyList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = layoutInflater.inflate(R.layout.list_row_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val (repositoryName, htmlUrl, ownerName, ownerAvatarUrl, starCount) = viewModels[position]
        holder.textViewRepositoryName.text = repositoryName
        holder.textViewRepositoryUrl.text = htmlUrl
        holder.textViewOwnerName.text = ownerName
        holder.textViewStarCount.text = starCount.toString()

        picasso
            .load(ownerAvatarUrl)
            .into(holder.imageViewOwnerThumbnail)
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    fun setViewModels(viewModels: List<RepositoryViewModel>) {
        this.viewModels = viewModels
        notifyDataSetChanged()
    }

    /**
     * view holder
     */
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.owner_thumbnail)
        lateinit var imageViewOwnerThumbnail: ImageView

        @BindView(R.id.repository_name)
        lateinit var textViewRepositoryName: TextView

        @BindView(R.id.owner_name)
        lateinit var textViewOwnerName: TextView

        @BindView(R.id.repository_url)
        lateinit var textViewRepositoryUrl: TextView

        @BindView(R.id.star_count)
        lateinit var textViewStarCount: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}