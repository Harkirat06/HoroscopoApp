package dadm.hsingh.horoscopoapp.data.friend

import dadm.hsingh.horoscopoapp.data.friend.model.FriendDto
import kotlinx.coroutines.flow.Flow

class FriendsDataSourceImpl : FriendsDataSource {
    override suspend fun addFriend(friend: FriendDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFriend(friend: FriendDto) {
        TODO("Not yet implemented")
    }

    override fun getAllFriend(): Flow<List<FriendDto>> {
        TODO("Not yet implemented")
    }

    override fun getFriendById(id: String): Flow<FriendDto?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllFriend() {
        TODO("Not yet implemented")
    }

}