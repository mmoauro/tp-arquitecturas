export class Service {
    protected baseUrl = "http://localhost:8080";

    constructor(endpoint: string) {
        this.baseUrl += endpoint;
    }

    protected async get<T> (endpoint: string) {
        this.baseUrl += endpoint;
        const respose = await fetch(this.baseUrl)
            .then(res => res.json());
        return respose;
    }

    public async post<T> (endpoint: string, body?: T): Promise<T> {
        this.baseUrl += endpoint;
        return await fetch(this.baseUrl, {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body)
        })
            .then(res => res.json());
    }

    public async delete<T> (endpoint: string): Promise<T> {
        this.baseUrl += endpoint;
        return await fetch(this.baseUrl, {
            method: "DELETE",
        })
            .then(res => res.json());

}
}