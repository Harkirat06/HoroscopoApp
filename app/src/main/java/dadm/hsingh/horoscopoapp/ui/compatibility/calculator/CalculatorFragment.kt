package dadm.hsingh.horoscopoapp.ui.compatibility.calculator

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentCalculatorBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.ui.compatibility.CompatibilityViewModel
import dadm.hsingh.horoscopoapp.ui.compatibility.friends.FriendsListAdapter
import kotlinx.coroutines.launch

class CalculatorFragment : Fragment(R.layout.fragment_calculator){

    private var _binding : FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CompatibilityViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCalculatorBinding.bind(view)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allFriends.collect {

                    val adapter = ArrayAdapter(requireContext(), R.layout.spinner_view, viewModel.getFriendNames())


                    val spinner: Spinner = binding.spinner1
                    val spinner2: Spinner = binding.spinner2

                    spinner.adapter = adapter
                    spinner2.adapter = adapter
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.secondFriend.collect {friend ->
                    if (friend != null) {
                        binding.imageviewPerson2.setImageResource(friend.defaultImage)
                    }


                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.firstFriend.collect {friend ->
                    if (friend != null) {
                        binding.imageviewPerson1.setImageResource(friend.defaultImage)
                    }


                }
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}