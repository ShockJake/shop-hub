import {setEventListener} from "../utils/utils.js";
import {editProduct} from "../actions/productManagement";

setEventListener("confirmEditionButton", () => editProduct())