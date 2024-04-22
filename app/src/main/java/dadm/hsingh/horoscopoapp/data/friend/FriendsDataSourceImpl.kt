package dadm.hsingh.horoscopoapp.data.friend

import dadm.hsingh.horoscopoapp.data.friend.model.FriendDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendsDataSourceImpl @Inject constructor(private val friendsDao: FriendsDao) : FriendsDataSource {
    override suspend fun addFriend(friend: FriendDto) {
        friendsDao.addFriend(friend)
    }

    override suspend fun deleteFriend(friend: FriendDto) {
        friendsDao.deleteFriend(friend)
    }

    override fun getAllFriend(): Flow<List<FriendDto>> {
        return friendsDao.getAllFriend()
    }

    override fun getFriendById(id: String): Flow<FriendDto?> {
        return friendsDao.getFriendById(id)
    }

    override fun getFriendByName(name: String): Flow<List<FriendDto>> {
        return friendsDao.getFriendByName(name)
    }

    override suspend fun deleteAllFriend() {
        friendsDao.deleteAllFriends()
    }

}