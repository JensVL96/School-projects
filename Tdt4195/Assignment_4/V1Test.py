# result = 0
    # for i in range(3):
    # Channel = np.array(im[:, :, i])

    # Flip the matrix horizontally then vertically
    # kernel = np.flipud(np.fliplr(kernel))

    size = im_size(im.shape[0], kernel.shape[0])
    pixels = im.copy()
    # size = R.shape[0]
    # Array of zeroes, width and height is the same size for all inputs
    # output = np.zeros(shape=(size, size))
    # size = int((R.shape[0] - kernel.shape[0]) + 1)

    # print("output:", len(output), "= size:", size)

    for x in range(size):   # rows
        for y in range(size):   # columns
            r, g, b = im.getpixel((x, y))

            iter_arr = np.array[[r], [g], [b]]
            output = np.dot(kernel, iter_arr)

            temp_r, temp_g, temp_b = int(output[0, 0]), int(output[1, 0]), int(output[2, 0])

            if temp_r > 255:
                temp_r = 255
            if temp_g > 255:
                temp_g = 255
            if temp_b > 255:
                temp_b = 255

            pixels[x, y] = (temp_r, temp_g, temp_b)

            # mat = Channel[x:x+kernel.shape[0], y:y+kernel.shape[0]]
            # # print("matrix:", mat.shape)
            # try:
            #     output[x, y] = np.sum(np.multiply(mat, kernel))
            # except:
            #     break

    # result += output

    return im