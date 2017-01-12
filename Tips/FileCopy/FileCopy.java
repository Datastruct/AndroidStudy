//File copy assets to sdcard

private void CopyFiles(){
	final String BASIC_PATH = /sdcard/Data/;
  	final String DIR_NAME = "my/config";

	String storageState = android.os.Environment.getExternalStorageState();
	AssetManager mAssetManager = getAssets();
  
	AssetsCopy(BASIC_PATH, DIR_NAME, mAssetManager, storageState);
}
  
private void AssetsCopy(String basicPath, String typeOfPro, AssetManager am, String storStat){
	try
	{
		String[] fileNames = am.list(typeOfPro);

    		if (storStat.equals(android.os.Environment.MEDIA_MOUNTED))
		{
			String dirPath = basicPath+typeOfPro;
      			File file = new File(dirPath);
			if(!file.exists()
			{
				file.mkdirs();
			}
		}
		//fileNames[] is source, ResName is destination.
		for (int i = 0; i < fileNames.length; i++)
    		{
			String ResName = basicPath+typeOfPro+"/"+fileNames[i];
			InputStream is = am.open(typeOfPro+"/"+fileNames[i]);

			byte assetBuffer[] = new byte[is.available()];
			is.read(assetBuffer);
			is.close();

			boolean isCopy = false;
		        exists = (new File(ResName)).exists();

			if (exists)
			{
				is = new BufferedInputStream(new FileInputStream(ResName));
				byte libBuffer[] = new byte[is.available()];
				is.read(libBuffer);
				is.close();

				isCopy = !Arrays.equals(assetBuffer, libBuffer);
			}
			else
				isCopy = true;

			if (isCopy)
			{
				// Write
				FileOutputStream fos = new FileOutputStream(ResName);
				fos.write(assetBuffer);
				fos.close();
			}
		}

	}
	catch (Exception e)
	{
      		Log.w("FileCopy", e.getMessage());
	}
}