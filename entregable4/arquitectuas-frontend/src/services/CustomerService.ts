import {Customer} from "../models/Customer";
import {Service} from "./Service";

export class CustomerService extends Service{

    constructor() {
        super("/customers");
    }

    public async getCustomers(): Promise<Customer[]> {
        return this.get<Customer>("");
    }
}