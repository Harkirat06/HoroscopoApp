package dadm.hsingh.horoscopoapp.ui.compatibility.friends

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentFriendsBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import java.time.LocalDate
import java.time.LocalTime

class FriendsFragment : Fragment(R.layout.fragment_friends){
    private var _binding : FragmentFriendsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFriendsBinding.bind(view)

        val adapter = FriendsListAdapter(::onItemClick)
        binding.textView.adapter = adapter

        adapter.submitList(generateRandomFriendsList(20))
    }

    private fun onItemClick(s: String) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun generateRandomFriendsList(size: Int): List<Friend> {
        val friendsList = mutableListOf<Friend>()
        val names = listOf("Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack")
        val places = listOf("New York", "Los Angeles", "London", "Paris", "Tokyo", "Sydney", "Berlin", "Rome", "Moscow", "Toronto")
        val zodiacImages = listOf(
            R.drawable.aries,
            R.drawable.taurus,
            R.drawable.gemini,
            R.drawable.cancer,
            R.drawable.leo,
            R.drawable.virgo,
            R.drawable.libra,
            R.drawable.scorpio,
            R.drawable.sagittarius,
            R.drawable.capricorn,
            R.drawable.aquarius,
            R.drawable.pisces
        )
        repeat(size) {
            val id = it.toString()
            val name = names.random()
            val dateBirth = generateRandomDate()
            val timeBirth = generateRandomTime()
            val placeBirth = places.random()
            val zodiacSignImage = zodiacImages.random()
            friendsList.add(Friend(id, name, dateBirth, timeBirth, placeBirth, zodiacSignImage))
        }
        return friendsList
    }

    private fun generateRandomDate(): LocalDate {
        val start = LocalDate.of(1950, 1, 1)
        val end = LocalDate.of(2005, 12, 31)
        val randomDay = start.toEpochDay() + (Math.random() * (end.toEpochDay() - start.toEpochDay())).toLong()
        return LocalDate.ofEpochDay(randomDay)
    }

    private fun generateRandomTime(): LocalTime {
        return LocalTime.of((0..23).random(), (0..59).random(), (0..59).random())
    }
}