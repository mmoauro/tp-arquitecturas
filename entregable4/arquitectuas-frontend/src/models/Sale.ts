import {Customer} from "./Customer";
import {Product} from "./Productt";

export interface Sale {
    id?: number;
    customer: Customer;
    product: Product;
}