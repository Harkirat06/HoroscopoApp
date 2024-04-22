package dadm.hsingh.horoscopoapp.data.friend

import dadm.hsingh.horoscopoapp.domain.model.Friend
import kotlinx.coroutines.flow.Flow

class FriendsRepositoryImpl : FriendsRepository{
    override suspend fun addFriend(friend: Friend) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFriend(friend: Friend) {
        TODO("Not yet implemented")
    }

    override fun getAllFriend(): Flow<List<Friend>> {
        TODO("Not yet implemented")
    }

    override fun getFriendById(id: String): Flow<Friend?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllFriend() {
        TODO("Not yet implemented")
    }
}