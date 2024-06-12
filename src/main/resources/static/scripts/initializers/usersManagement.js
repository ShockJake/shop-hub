import {deleteUser, editUser, setUserDataToEditModal, setUserIdToDeleteModal} from "../actions/usersManagement.js";

import {setEventListener, setEventListeners} from "../utils/utils.js";

setEventListeners('delete-user-button', (e) => setUserIdToDeleteModal(e.target.id));
setEventListeners('edit-user-button', (e) => setUserDataToEditModal(e.target.id))
setEventListener("confirmEditUserDataButton", () => editUser())
setEventListener("confirmDeletionOfUserButton", () => deleteUser())
