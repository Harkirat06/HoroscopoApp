package dadm.hsingh.horoscopoapp.ui.compatibility.calculator

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
                viewModel.allFriends.collect { friendsList ->

                    val adapter = ArrayAdapter(requireContext(), R.layout.spinner_view, viewModel.getFriendNames())

                    val spinner: Spinner = binding.spinner1
                    val spinner2: Spinner = binding.spinner2

                    spinner.adapter = adapter
                    spinner2.adapter = adapter

                    // Agregar un listener al Spinner
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            // Acción a realizar cuando se selecciona un elemento del Spinner1
                            val selectedFriend = friendsList[position]
                            // Realiza la acción que deseas con el elemento seleccionado
                            // Por ejemplo:
                             viewModel.setFirstFriend(selectedFriend)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // No hagas nada cuando no se selecciona ningún elemento
                        }
                    }

                    // Agregar un listener al segundo Spinner si es necesario
                    spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            // Acción a realizar cuando se selecciona un elemento del Spinner2
                            val selectedFriend = friendsList[position]
                            // Realiza la acción que deseas con el elemento seleccionado
                            // Por ejemplo:
                            viewModel.setSecondFriend(selectedFriend)

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // No hagas nada cuando no se selecciona ningún elemento
                        }
                    }
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.secondFriend.collect {friend ->
                    if (friend != null) {
                        if (friend.imageUri != null){
                            binding.imageviewPerson2.setImageURI(Uri.parse(friend.imageUri))
                        }else{
                            binding.imageviewPerson2.setImageResource(friend.defaultImage)
                        }
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.firstFriend.collect {friend ->
                    if (friend != null) {
                        if (friend.imageUri != null){
                            binding.imageviewPerson1.setImageURI(Uri.parse(friend.imageUri))
                        }else{
                            binding.imageviewPerson1.setImageResource(friend.defaultImage)
                        }
                    }
                }
            }
        }

        binding.buttonCalculate.setOnClickListener{

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}