import {
    setCategoryDataToEditModal,
    editCategory,
    setCategoryDataToDeleteModal,
    deleteCategory
} from "../actions/categoryManagement.js";

import {setEventListener, setEventListeners} from "../utils/utils.js";

setEventListeners("delete-category-button", e => setCategoryDataToDeleteModal(e.target.id));
setEventListeners("edit-category-button", e => setCategoryDataToEditModal(e.target.id));
setEventListener("confirmEditCategoryDataButton", () => editCategory())
setEventListener("confirmDeletionOfCategoryButton", () => deleteCategory())
