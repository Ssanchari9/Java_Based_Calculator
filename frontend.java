import { useState } from "react";

export default function Calculator() {
  const [num1, setNum1] = useState("");
  const [num2, setNum2] = useState("");
  const [operator, setOperator] = useState("+");
  const [result, setResult] = useState(null);

  const calculate = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/calculate?num1=${num1}&num2=${num2}&operator=${operator}`);
      const data = await response.json();
      setResult(data);
    } catch (error) {
      console.error("Error fetching result:", error);
    }
  };

  return (
    <div className="flex flex-col items-center gap-4 p-4 border rounded-lg w-96 mx-auto mt-10">
      <h1 className="text-xl font-bold">Calculator</h1>
      <input type="number" placeholder="First number" value={num1} onChange={(e) => setNum1(e.target.value)} className="border p-2 rounded w-full" />
      <select value={operator} onChange={(e) => setOperator(e.target.value)} className="border p-2 rounded w-full">
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
      </select>
      <input type="number" placeholder="Second number" value={num2} onChange={(e) => setNum2(e.target.value)} className="border p-2 rounded w-full" />
      <button onClick={calculate} className="bg-blue-500 text-white p-2 rounded w-full">Calculate</button>
      {result !== null && <h2 className="text-lg font-bold">Result: {result}</h2>}
    </div>
  );
}
