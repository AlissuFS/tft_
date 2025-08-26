import React, { useState } from "react";

function App() {
  const [apkLink, setApkLink] = useState("");

  const generateAPK = async () => {
    alert("Workflow de build disparado! Após finalizar, APK estará disponível.");
    setApkLink("https://github.com/seu-usuario/tft-coach/some-artifact-link");
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold">TFT Coach Dashboard</h1>
      <button
        onClick={generateAPK}
        className="mt-4 px-4 py-2 bg-purple-800 text-white rounded-lg"
      >
        Gerar APK
      </button>
      {apkLink && (
        <div className="mt-4">
          <a href={apkLink} target="_blank" rel="noreferrer" className="text-blue-500 underline">
            Baixar APK
          </a>
        </div>
      )}
    </div>
  );
}

export default App;
