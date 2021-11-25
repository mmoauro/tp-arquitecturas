import {Service} from "./Service";
import {Product} from "../models/Productt";

export class ProductService extends Service{

    constructor() {
        super("/products");
    }

    public async getProducts(): Promise<Product[]> {
        return this.get<Product>("");
    }

    public async createProduct(product: Product): Promise<Product> {
        return this.post("", product);
    }
}