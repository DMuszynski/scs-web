import {Product} from "./product";
import {User} from "./user";

export interface Transaction {
  id?: number;
  date?: string;
  product?: Product;
  user?: User;
}
