import { Hotel } from "./Hotel";
import { Agency } from "./Agency";

export class Arrangement {
    id!: number;
    total_cost!: number;
    payed!: boolean;
    realization_date!: Date;
    hotel!: Hotel;
    agency!: Agency;
}
